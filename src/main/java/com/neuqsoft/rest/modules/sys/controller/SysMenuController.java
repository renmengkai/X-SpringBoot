package com.neuqsoft.rest.modules.sys.controller;

import java.util.List;
import java.util.Set;

import com.neuqsoft.rest.common.annotation.AuthIgnore;
import com.neuqsoft.rest.common.annotation.SysLog;
import com.neuqsoft.rest.common.base.AbstractController;
import com.neuqsoft.rest.common.exception.RRException;
import com.neuqsoft.rest.common.utils.R;
import com.neuqsoft.rest.modules.sys.entity.SysMenu;
import com.neuqsoft.rest.modules.sys.service.PermissionsService;
import com.neuqsoft.rest.modules.sys.service.SysMenuService;
import com.neuqsoft.rest.modules.sys.vo.RouterEntity;
import com.neuqsoft.rest.common.utils.Constant.MenuType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统菜单
 *
 * @author neuqsoft
 */
@Slf4j
@RestController
@RequestMapping("/sys/menu")
@AllArgsConstructor
public class SysMenuController extends AbstractController {
	private final SysMenuService sysMenuService;

	private final PermissionsService shiroService;

	/**
	 * 导航菜单
	 */
	@RequestMapping("/nav")
	public R nav() {
		List<SysMenu> menuList = sysMenuService.getUserMenuList(getUserId());
		Set<String> permissions = shiroService.getUserPermissions(getUserId());
		return R.ok().put("menuList", menuList).put("permissions", permissions);
	}

	@AuthIgnore
	@RequestMapping("/getRouter")
	public R getRouter() {
		List<RouterEntity> userMenu = sysMenuService.getUserMenu(1l);
		log.info("========{}", userMenu);
		return R.ok().put("data", userMenu);
	}

	/**
	 * 所有菜单列表
	 */
	@RequestMapping("/list")
	@PreAuthorize("hasRole('sys:menu:list')")
	public List<SysMenu> list() {
		List<SysMenu> menuList = sysMenuService.list();

		return menuList;
	}

	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@RequestMapping("/select")
	@PreAuthorize("hasRole('sys:menu:select')")
	public R select() {
		//查询列表数据
		List<SysMenu> menuList = sysMenuService.queryNotButtonList();

		//添加顶级菜单
		SysMenu root = new SysMenu();
		root.setMenuId(0L);
		root.setName("一级菜单");
		root.setParentId(-1L);
		root.setOpen(true);
		menuList.add(root);

		return R.ok().put("menuList", menuList);
	}

	/**
	 * 菜单信息
	 */
	@RequestMapping("/info/{menuId}")
	@PreAuthorize("hasRole('sys:menu:info')")
	public R info(@PathVariable("menuId") Long menuId) {
		SysMenu menu = sysMenuService.getById(menuId);
		return R.ok().put("menu", menu);
	}

	/**
	 * 保存
	 */
	@SysLog("保存菜单")
	@RequestMapping("/save")
	@PreAuthorize("hasRole('sys:menu:save')")
	public R save(@RequestBody SysMenu menu) {
		//数据校验
		verifyForm(menu);

		sysMenuService.save(menu);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改菜单")
	@RequestMapping("/update")
	@PreAuthorize("hasRole('sys:menu:update')")
	public R update(@RequestBody SysMenu menu) {
		//数据校验
		verifyForm(menu);

		sysMenuService.updateById(menu);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除菜单")
	@RequestMapping("/delete")
	@PreAuthorize("hasRole('sys:menu:delete')")
	public R delete(long menuId) {
		//判断是否有子菜单或按钮
		List<SysMenu> menuList = sysMenuService.queryListParentId(menuId);
		if (menuList.size() > 0) {
			return R.error("请先删除子菜单或按钮");
		}

		sysMenuService.removeById(menuId);

		return R.ok();
	}

	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(SysMenu menu) {
		if (StringUtils.isBlank(menu.getName())) {
			throw new RRException("菜单名称不能为空");
		}

		if (menu.getParentId() == null) {
			throw new RRException("上级菜单不能为空");
		}

		//菜单
		if (menu.getType() == MenuType.MENU.getValue()) {
			if (StringUtils.isBlank(menu.getUrl())) {
				throw new RRException("菜单URL不能为空");
			}
		}

		//上级菜单类型
		int parentType = MenuType.CATALOG.getValue();
		if (menu.getParentId() != 0) {
			SysMenu parentMenu = sysMenuService.getById(menu.getParentId());
			parentType = parentMenu.getType();
		}

		//目录、菜单
		if (menu.getType() == MenuType.CATALOG.getValue() ||
				menu.getType() == MenuType.MENU.getValue()) {
			if (parentType != MenuType.CATALOG.getValue()) {
				throw new RRException("上级菜单只能为目录类型");
			}
			return;
		}

		//按钮
		if (menu.getType() == MenuType.BUTTON.getValue()) {
			if (parentType != MenuType.MENU.getValue()) {
				throw new RRException("上级菜单只能为菜单类型");
			}
			return;
		}
	}
}

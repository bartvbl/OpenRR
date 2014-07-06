from ore import on, spawn, gui

def initSidebars():
	orr_activeSideMenu = 'sideMainMenu'

	def registerSideMenuSwitch(actionName, menuName):
		@on('GUI_Click', action=actionName)
		def switchToBuildingMenu(eventParam):
			global orr_activeSideMenu
			orr_activeSideMenu = menuName
			gui.animateMenu('sideMainMenu', 'hideSidebar')
			
		@on('GUI_AnimationComplete', menuName=menuName)
		def swapMenus(eventParams):
			global orr_activeSideMenu
			if orr_activeSideMenu != 'sideMainMenu':
				return
			gui.show('sideMainMenu')
			gui.hide(menuName)
			gui.animateMenu('sideMainMenu', 'showSidebar')
			
	registerSideMenuSwitch('switchToBuildingMenu', 'buildMenu')
	registerSideMenuSwitch('switchToSmallVehiclesMenu', 'buildSmallVehicles')
	registerSideMenuSwitch('switchToLargeVehiclesMenu', 'buildLargeVehicles')
	
	@on('GUI_AnimationComplete', menuName='sideMainMenu')
	def swapMenus(eventParams):
		global orr_activeSideMenu
		if orr_activeSideMenu == 'sideMainMenu':
			return
		gui.hide('sideMainMenu')
		gui.show(orr_activeSideMenu)
		gui.animateMenu(orr_activeSideMenu, 'showSidebar')

	@on('GUI_Click', action="switchToMain")
	def returnToMainSidebar(eventParams):
		global orr_activeSideMenu
		gui.animateMenu(orr_activeSideMenu, 'hideSidebar')
		orr_activeSideMenu = 'sideMainMenu'
		
	@on('GUI_Click', action='buildToolStore')
	def buildToolStore(eventParams):
		spawn('TOOL_STORE_PLACER')

	@on('GUI_Click', action='buildSmallTeleport')
	def buildSmallTeleport(eventParams):
		spawn('SMALL_TELEPORT_PLACER')
		
	@on('GUI_Click', action='buildDock')
	def buildDock(eventParams):
		spawn('DOCK_PLACER')
		
	@on('GUI_Click', action='buildPowerStation')
	def buildPowerStation(eventParams):
		spawn('POWER_STATION_PLACER')
		
	@on('GUI_Click', action='buildRefinery')
	def buildOreRefinery(eventParams):
		spawn('ORE_REFINERY_PLACER')
		
	@on('GUI_Click', action='buildSupportStation')
	def buildSupportStation(eventParams):
		spawn('SUPPORT_STATION_PLACER')
		
	@on('GUI_Click', action='buildSuperTeleport')
	def buildSuperTeleport(eventParams):
		spawn('SUPER_TELEPORT_PLACER')
		
	@on('GUI_Click', action='buildSnackbar')
	def buildSnackbar(eventParams):
		spawn('SNACKBAR_PLACER')
		
	@on('GUI_Click', action='buildUpgradeStation')
	def buildUpgradeStation(eventParams):
		spawn('UPGRADE_STATION_PLACER')
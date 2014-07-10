from ore import on, spawn, gui

orr_sideMenuState = 0
orr_activeSideMenu = 'sideMainMenu'

def registerSideMenuSwitch(actionName, menuName, showAnimation, hideAnimation, returnAction):
	#Hide sidebar
	@on('GUI_Click', action=actionName)
	def switchToBuildingMenu(eventParam):
		global orr_sideMenuState
		global orr_activeSideMenu
		if orr_sideMenuState == 0:
			orr_activeSideMenu = menuName
			gui.animateMenu('sideMainMenu', 'hideSidebar')
			orr_sideMenuState = 1
	
	#When sidebar hidden, show new menu
	@on('GUI_AnimationComplete', menuName='sideMainMenu')
	def swapMenus(eventParams):
		global orr_sideMenuState
		global orr_activeSideMenu
		if orr_sideMenuState == 1 and orr_activeSideMenu == menuName:
			gui.hide('sideMainMenu')
			gui.show(menuName)
			gui.animateMenu(menuName, showAnimation)
			orr_sideMenuState = 2
	
	#The menu is now active.
	
	#When a return button is clicked, hide menu
	@on('GUI_Click', action=returnAction)
	def returnFromRegularSidebar(eventParams):
		global orr_sideMenuState
		global orr_activeSideMenu
		if orr_sideMenuState == 2 and orr_activeSideMenu == menuName:
			gui.animateMenu(menuName, hideAnimation)
			orr_sideMenuState = 3
	
	#When menu is hidden, show the sidebar
	@on('GUI_AnimationComplete', menuName=menuName)
	def swapMenus(eventParams):
		global orr_sideMenuState
		global orr_activeSideMenu
		if orr_sideMenuState == 3 and orr_activeSideMenu == menuName:
			orr_activeSideMenu = 'sideMainMenu'
			gui.show('sideMainMenu')
			gui.hide(menuName)
			gui.animateMenu('sideMainMenu', 'showSidebar')
			orr_sideMenuState = 0
		
registerSideMenuSwitch('switchToBuildingMenu', 'buildMenu', 'showSidebar', 'hideSidebar', 'switchToMain')
registerSideMenuSwitch('switchToSmallVehiclesMenu', 'buildSmallVehicles', 'showSidebar', 'hideSidebar', 'switchToMain')
registerSideMenuSwitch('switchToLargeVehiclesMenu', 'buildLargeVehicles', 'showSidebar', 'hideSidebar', 'switchToMain')
registerSideMenuSwitch('togglePrioritiesPanel', 'prioritiesPanel', 'showPrioritiesPanel', 'hidePrioritiesPanel', 'togglePrioritiesPanel')
	
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
		
@on('GUI_Click', action='buildMiningLaser')
def buildUpgradeStation(eventParams):
	spawn('MINING_LASER_PLACER')
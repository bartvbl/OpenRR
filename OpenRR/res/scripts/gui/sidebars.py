from ore import on, spawn, gui, ai
from orr import registerTask

orr_sideMenuState = 0
orr_activeSideMenu = 'sideMainMenu'
orr_currentHideAnimation = 'hideSidebar'
orr_currentSwitchAction = ''
orr_currentActionName = ''


def registerTransition(actionName, menuName, switchAction, showAnimation, hideAnimation):
	global orr_activeSideMenu

	#Hide sidebar
	@on(switchAction, action=actionName)
	def switchToBuildingMenu(eventParam):
		global orr_sideMenuState
		global orr_activeSideMenu
		global orr_currentHideAnimation
		global orr_currentSwitchAction
		global orr_currentActionName

		if orr_sideMenuState == 0:
			gui.animateMenu(orr_activeSideMenu, orr_currentHideAnimation)
			orr_sideMenuState = 1
			orr_currentSwitchAction = switchAction
			orr_currentActionName = actionName
	
	#When sidebar hidden, show new menu
	@on('GUI_AnimationComplete')
	def swapMenus(eventParams):
		global orr_sideMenuState
		global orr_activeSideMenu
		global orr_currentHideAnimation
		global orr_currentSwitchAction
		global orr_currentActionName

		if orr_sideMenuState == 1 and orr_currentSwitchAction == switchAction and orr_currentActionName == actionName:
			gui.hide(orr_activeSideMenu)
			gui.show(menuName)
			orr_activeSideMenu = menuName
			gui.animateMenu(menuName, showAnimation)
			orr_sideMenuState = 0
			orr_currentHideAnimation = hideAnimation
	
	#The menu is now active.
	
	#When a return button is clicked, hide menu
	'''@on('GUI_Click', action=returnAction)
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
						orr_sideMenuState = 0'''

registerTransition('switchToMain', 'sideMainMenu', 'GUI_Click', 'showSidebar', 'hideSidebar')

registerTransition('switchToBuildingMenu', 'buildMenu', 'GUI_Click', 'showSidebar', 'hideSidebar')
registerTransition('switchToSmallVehiclesMenu', 'buildSmallVehicles', 'GUI_Click', 'showSidebar', 'hideSidebar')
registerTransition('switchToLargeVehiclesMenu', 'buildLargeVehicles', 'GUI_Click', 'showSidebar', 'hideSidebar')
registerTransition('togglePrioritiesPanel', 'prioritiesPanel', 'GUI_Click', 'showPrioritiesPanel', 'hidePrioritiesPanel') #, 'togglePrioritiesPanel'
registerTransition('wall', 'wallDrillMenu', 'showTileSelectionMenu', 'showSidebar', 'hideSidebar')
registerTransition('floor', 'groundSelectionMenu', 'showTileSelectionMenu', 'showSidebar', 'hideSidebar')
	
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


@on('GUI_Click', action='teleportRockRaider')
def teleportRockRaider(eventParams):
	registerTask('TELEPORT_ROCK_RAIDER')
@on('gameStateChanged', newState='GAME_RUNNING')
def buildMainScene(params):
	spawn('MAP')
	spawn('MAP_CONTROLLER')
	spawn('ROCK_RAIDER')
	spawn('MOUSE_TRACKER')
	spawn('FLASHLIGHT')
	
	#set up UI
	gui.show('notificationPanel')
	gui.show('radarPanel')
	gui.show('topMainMenu')
	gui.show('resourceSidebar')
	gui.show('sideMainMenu')

#Side menu
	
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
	
@on('GUI_Click', action='buildDock')
def buildToolStore(eventParams):
	spawn('DOCK_PLACER')
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
	gui.show('buildMenu')
		
@on('GUI_Click', action='switchToBuildingMenu')
def switchToBuildingMenu(eventParam):
	gui.animateMenu('sideMainMenu', 'hideSidebar')
	
@on('GUI_AnimationComplete', menuName='sideMainMenu')
def swapMenus(eventParams):
	gui.hide('sideMainMenu')
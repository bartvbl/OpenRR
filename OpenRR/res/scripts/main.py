@on('gameStateChanged')
def buildMainScene(eventParam):
	if eventParam == 'GAME_RUNNING':
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
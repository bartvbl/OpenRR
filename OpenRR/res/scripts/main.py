@on('gameStateChanged')
def buildMainScene(eventParam):
	if eventParam == 'GAME_RUNNING':
		spawn('MAP')
		spawn('MAP_CONTROLLER')
		spawn('ROCK_RAIDER')
		spawn('MOUSE_TRACKER')
		spawn('FLASHLIGHT')
		gui.show('topMainMenu')
		gui.show('sideMainMenu')
		gui.show('radarPanel')
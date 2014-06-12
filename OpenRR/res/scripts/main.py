def buildMainScene(eventParam):
	if eventParam == 'GAME_RUNNING':
		spawn('MAP')
		spawn('MAP_CONTROLLER')
	
on('gameStateChanged', buildMainScene)
def buildMainScene(eventParam):
	if eventParam == 'GAME_RUNNING':
		spawn('MAP')
		spawn('MAP_CONTROLLER')
		spawn('TOOL_STORE')
		spawn('ROCK_RAIDER')
	
on('gameStateChanged', buildMainScene)
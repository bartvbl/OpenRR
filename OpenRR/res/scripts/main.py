@on('gameStateChanged', newState='GAME_RUNNING')
def buildMainScene(params):
	spawn('MAP')
	spawn('MAP_CONTROLLER')
	spawn('ROCK_RAIDER')
	spawn('MOUSE_TRACKER')
	spawn('FLASHLIGHT')
	
	#set up UI
	gui.show('notificationPanel')
	gui.show('radarPanel_retracted')
	gui.show('topMainMenu')
	gui.show('resourceSidebar')
	gui.show('sideMainMenu')

from gui.sidebars import *
from gui.radarScreen import *
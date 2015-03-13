@on('gameStateChanged', newState='GAME_RUNNING')
def buildMainScene(params):
	spawn('MAP')
	spawn('MAP_CONTROLLER')
	spawn('PRIORITIES_UPDATER')
	spawn('ORE')
	spawn('MOUSE_TRACKER')
	spawn('FLASHLIGHT')
	spawn('TILE_SELECTOR')
	
	#set up UI
	gui.show('notificationPanel')
	gui.show('radarPanel_retracted')
	gui.show('topMainMenu')
	gui.show('resourceSidebar')
	gui.show('sideMainMenu')

from gui.sidebars import *
from gui.radarScreen import *
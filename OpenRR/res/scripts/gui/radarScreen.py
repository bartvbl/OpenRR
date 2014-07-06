from ore import on, spawn, gui

orr_radarScreenState = False

@on('GUI_Click', action="toggleRadarPanel")
def expandRadarPanel(eventParams):
	global orr_radarScreenState
	if orr_radarScreenState:
		gui.animateMenu('radarPanel', 'hideRadarPanel')
	else:
		gui.show('radarPanel')
		gui.hide('radarPanel_retracted')
		gui.animateMenu('radarPanel', 'showRadarPanel')
	orr_radarScreenState = not orr_radarScreenState
	
@on('GUI_AnimationComplete', menuName='radarPanel')
def resetRadarPanel(eventParams):
	global orr_radarScreenState
	if not orr_radarScreenState:
		gui.hide('radarPanel')
		gui.show('radarPanel_retracted')
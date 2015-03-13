from ore import ai

from openrr.ai import TaskType
from openrr.ai.tasks import ORRTask, TeleportRockRaiderTask

def registerTask(taskType, objectID = -1):
	type = TaskType.valueOf(taskType)
	task = None
	if type == TaskType.TELEPORT_ROCK_RAIDER:
		task = TeleportRockRaiderTask()

	if task is not None:
		ai.registerTask(task)
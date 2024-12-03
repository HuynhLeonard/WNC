import { setCompleted, getTasks } from "../api/todo.js";
import { useTaskContext } from "../useTaskContext.js";

export default function TaskList() {
  const { state, dispatch } = useTaskContext();

  const filteredTasks = state.tasks.filter((task) =>
    task.todoItem.toLowerCase().includes(state.filter.toLowerCase())
  );

  async function handleStateChange(task) {
    dispatch({ type: "SET_LOADING", payload: true });

    await setCompleted(task, !task.completed);

    const taskList = await getTasks();
    dispatch({ type: "SET_TASK_LIST", payload: taskList });

    dispatch({ type: "SET_LOADING", payload: false });
  }

  return (
    <div className="flex flex-wrap max-w-6xl mx-auto mt-10 gap-y-5">
      {filteredTasks.map((task) => (
        <div key={task.id} className="basis-1/4">
          <div className="flex flex-col items-start h-[250px] p-2 bg-amber-300 hover:bg-amber-400 has-[:checked]:bg-yellow-200 has-[:checked]:hover:bg-yellow-300 rounded-md mx-1 shadow-lg">
            <input
              checked={task.completed}
              onChange={() => handleStateChange(task)}
              type="checkbox"
              className="peer min-w-5 min-h-5 hover:cursor-pointer"
            />
            <div className="mt-2 overflow-auto grow peer-checked:text-gray-500 peer-checked:line-through">
              <h5 className="text-md">{task.todoItem}</h5>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
}

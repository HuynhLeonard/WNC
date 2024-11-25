import { useTaskContext } from "./useTaskContext.js";

function App() {
  const { state, dispatch } = useTaskContext();
  const { tasks, taskName, filter } = state;

  const filteredTasks = tasks.filter((task) =>
    task.name.toLowerCase().includes(filter.toLowerCase())
  );

  return (
    <div>
      <div className="flex justify-center">
        <div className="inline-flex flex-col items-start">
          <div className="flex mt-10 overflow-hidden rounded-md">
            <input
              value={taskName}
              onChange={(e) =>
                dispatch({ type: "SET_TASK_NAME", payload: e.target.value })
              }
              placeholder="Add new task"
              className="w-[600px] bg-gray-100 outline-none border-none py-2 px-3"
            />
            <button
              onClick={() => dispatch({ type: "ADD_TASK" })}
              className="w-40 p-2 font-medium text-white bg-sky-500 hover:bg-sky-400"
            >
              Add
            </button>
          </div>
          <div className="flex items-center mt-5">
            <h3 className="mr-2 text-xl">Filter:</h3>
            <input
              value={filter}
              onChange={(e) =>
                dispatch({ type: "SET_FILTER", payload: e.target.value })
              }
              placeholder="Filter by name"
              className="w-[300px] bg-gray-100 outline-none border-none rounded-md p-2"
            />
          </div>
        </div>
      </div>

      <div className="flex flex-wrap max-w-6xl mx-auto mt-10 gap-y-5">
        {filteredTasks.map((task) => (
          <div key={task.id} className="basis-1/4">
            <div className="flex flex-col items-start h-[250px] p-2 bg-amber-300 hover:bg-amber-400 has-[:checked]:bg-yellow-200 has-[:checked]:hover:bg-yellow-300 rounded-md mx-1 shadow-lg">
              <input
                type="checkbox"
                className="peer min-w-5 min-h-5 hover:cursor-pointer"
              />
              <div className="mt-2 overflow-auto grow peer-checked:text-gray-500 peer-checked:line-through">
                <h5 className="text-md">{task.name}</h5>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;

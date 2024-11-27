import { useTaskContext } from "./useTaskContext.js";

export default function AddTask() {
  const { state, dispatch } = useTaskContext();

  return (
    <div className="flex mt-10 overflow-hidden rounded-md">
      <input
        value={state.taskName}
        onChange={(e) => {
          dispatch({ type: "SET_TASK_NAME", payload: e.target.value });
        }}
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
  );
}

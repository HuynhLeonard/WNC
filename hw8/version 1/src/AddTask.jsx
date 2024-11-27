export default function AddTask({ taskName, setTaskName, handleAddTask }) {
  return (
    <div className="flex mt-10 overflow-hidden rounded-md">
      <input
        value={taskName}
        onChange={(e) => {
          setTaskName(e.target.value);
        }}
        placeholder="Add new task"
        className="w-[600px] bg-gray-100 outline-none border-none py-2 px-3"
      />
      <button
        onClick={handleAddTask}
        className="w-40 p-2 font-medium text-white bg-sky-500 hover:bg-sky-400"
      >
        Add
      </button>
    </div>
  );
}

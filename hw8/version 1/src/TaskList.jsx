export default function TaskList({ tasks }) {
  return (
    <div className="flex flex-wrap max-w-6xl mx-auto mt-10 gap-y-5">
      {tasks.map((task) => (
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
  );
}

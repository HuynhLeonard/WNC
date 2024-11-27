import { useTaskContext } from "./useTaskContext.js";

export default function FilterTask() {
  const { state, dispatch } = useTaskContext();

  return (
    <div className="flex items-center mt-5">
      <h3 className="mr-2 text-xl">Filter:</h3>
      <input
        value={state.filter}
        onChange={(e) => {
          dispatch({ type: "SET_FILTER", payload: e.target.value });
        }}
        placeholder="Filter by name"
        className="w-[300px] bg-gray-100 outline-none border-none rounded-md p-2"
      />
    </div>
  );
}

import { useForm } from "react-hook-form";

export default function Loginpage() {
  const { register, handleSubmit } = useForm();

  const handleLogin = (data) => console.log(data); // call login api

  return (
    <div className="flex items-center justify-center min-h-screen">
      <div className="w-full max-w-sm p-6 bg-white rounded shadow-md border">
        <h1 className="text-2xl font-semibold text-center text-gray-700">
          Login
        </h1>
        <form className="mt-6" onSubmit={handleSubmit(handleLogin)}>
          <div className="mb-4">
            <label
              htmlFor="username"
              className="block text-sm font-medium text-gray-600"
            >
              Username
            </label>
            <input
              type="text"
              id="username"
              {...register("username", { required: true })}
              className="w-full px-4 py-2 mt-2 text-gray-700 border rounded-lg focus:outline-none focus:ring focus:ring-blue-300"
            />
          </div>
          <div className="mb-4">
            <label
              htmlFor="password"
              className="block text-sm font-medium text-gray-600"
            >
              Password
            </label>
            <input
              type="password"
              id="password"
              {...register("password", { required: true })}
              className="w-full px-4 py-2 mt-2 text-gray-700 border rounded-lg focus:outline-none focus:ring focus:ring-blue-300"
            />
          </div>
          <button
            type="submit"
            className="w-full px-4 py-2 text-white bg-sky-500 hover:bg-sky-400 rounded-lg focus:outline-none "
          >
            Login
          </button>
        </form>
      </div>
    </div>
  );
}
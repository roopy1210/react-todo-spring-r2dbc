import axios from "axios";
import React, { useState, useEffect } from "react";
import { AiOutlinePlus } from "react-icons/ai";
import Todo from "./Todo";

const style = {
  bg: `h-screen w-screen p-4 bg-gradient-to-r from-indigo-500 via-purple-500 to-pink-500`,
  container: `bg-slate-100 max-w-[500px] m-auto rounded-md shadow-xl p-4`,
  heading: `text-3xl font-bold text-center text-gray-800 p-2`,
  form: `flex justify-between mt-2`,
  input: `border p-2 w-full text-xl`,
  button: `border p-4 ml-2 bg-purple-500 text-slate-100`,
  count: `text-center font-bold p-2`,
};

function App() {
  const [todos, setTodos] = useState([]);
  const [comment, setComment] = useState("");

  const commentChangeHandler = (event) => {
    setComment(event.target.value);
  };

  // Create Todo
  const submitHandler = (event) => {
    event.preventDefault();

    const enteredComment = comment;

    axios
      .post("http://localhost:8001/todos", {
        comment: enteredComment,
      })
      .then((response) => {
        const newTodos = [...todos, response.data];
        setTodos(newTodos);
        setComment("");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  // Read Todo
  useEffect(() => {
    axios
      .get("http://localhost:8001/todos")
      .then((response) => {
        setTodos(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  // Update Todo
  const toggleComplete = async (todo) => {
    await axios
      .put(`http://localhost:8001/todos/${todo.id}`, {
        completed: !todo.completed,
      })
      .then((response) => {
        const newTodos = todos.map((todo) => {
          if (todo.id === response.data.id) {
            return { ...todo, completed: response.data.completed };
          }

          return todo;
        });

        setTodos(newTodos);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  // Delete Todo
  const deleteTodo = async (id) => {
    await axios
      .delete(`http://localhost:8001/todos/${id}`)
      .then((response) => {
        if (response.status === 200) {
          const newTodos = todos.filter((todo) => todo.id !== id);
          setTodos(newTodos);
        }
      })
      .catch((error) => {
        console.log(error);
      });
    console.log(id);
  };

  return (
    <div className={style.bg}>
      <div className={style.container}>
        <h3 className={style.heading}>Todo App</h3>
        <form className={style.form} onSubmit={submitHandler}>
          <input
            className={style.input}
            type="text"
            placeholder="Add Todo"
            onChange={commentChangeHandler}
            value={comment}
          />
          <button className={style.button}>
            <AiOutlinePlus size={30} />
          </button>
        </form>
        <ul>
          {todos.map((todo, index) => (
            <Todo
              key={todo.id}
              todo={todo}
              toggleComplete={toggleComplete}
              deleteTodo={deleteTodo}
            />
          ))}
        </ul>
        <p className={style.count}>{`You have ${todos.length} todos`}</p>
      </div>
    </div>
  );
}

export default App;

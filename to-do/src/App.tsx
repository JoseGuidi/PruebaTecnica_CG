import "./App.css";
import { useState } from "react";
import { Formulario } from "./Formulario";
import { ListadoTareas } from "./ListadoTareas";

function App() {

  const [tareaActual, setTareaActual] = useState("");
  const [listado, setListado] = useState<
    {id:number, tarea: string; completado: boolean,fechaAgregada:Date }[]
  >([]);
  const [ultimoIdIngresado,setUltimoIdIngresado] = useState<number>(1)
  const [pendientes,setPendientes] = useState<number>(0);
  const [error,setError]=useState<boolean>(false);
  
  function agregarTarea(e) {
    e.preventDefault();
    if (tareaActual !== "") {
      const tarea = {
        id:ultimoIdIngresado,
        tarea: tareaActual,
        completado: false,
        fechaAgregada:new Date()
      };
      const auxArray = [tarea, ...listado];
      setListado(auxArray);
      setTareaActual("");
      setUltimoIdIngresado(ultimoIdIngresado+1)
      setPendientes(pendientes+1);
      setError(false)
    }else{
      setError(true)
    }
  }
  function cambioTarea(e) {
    setTareaActual(e.target.value);
  }
  function marcarCompletada(tarea) {
    listado.forEach((t) => {
      if (t.id === tarea.id) {
        t.completado = !t.completado;
        if(t.completado){
          setPendientes(pendientes-1)
        }else{
          setPendientes(pendientes+1)
        }
      }
    });
    
    const aux = [...listado];
    setListado(aux);
  }


  return (
    <>
      <div className="app-container">
        <h1>To Do List</h1>
        <Formulario
          tarea={tareaActual}
          cambioTarea={cambioTarea}
          agregarTarea={agregarTarea}
          error={error}
        />
        <ListadoTareas listado={listado} onClickTarea={marcarCompletada} />
        <h2>Tareas pendientes: {pendientes}</h2>
      </div>
    </>
  );
}

export default App;

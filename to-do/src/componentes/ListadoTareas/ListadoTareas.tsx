import { List } from "@mui/material";
import { Tarea } from "../Tarea/Tarea";
import './ListadoTareas.css'
export function ListadoTareas(props) {
  const { listado, onClickTarea } = props;
  return (
    <>

      <List className="listado">
        {
          listado.map((tarea, index) => (
            <Tarea tarea={tarea} key={index} marcarCompletada={onClickTarea}></Tarea>
          ))
        }
      </List>
    </>
  );
}

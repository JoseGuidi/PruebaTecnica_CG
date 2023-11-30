import { ListItem, ListItemText } from "@mui/material";
import './Tarea.css'
export function Tarea(props) {
    const { tarea, marcarCompletada } = props

    function cambiarEstadoTarea() {
        marcarCompletada(tarea)
    }
    return (
        <>
            <ListItem onClick={marcarCompletada} className="listado-tarea">
                <ListItemText primary={tarea.tarea} secondary={tarea.fechaAgregada.toString().slice(0, 24)} onClick={cambiarEstadoTarea} className={tarea.completado ? 'listado-app-tachado' : ''} />
            </ListItem>
        </>
    )
}
import { Button, TextField } from "@mui/material";

export function Formulario(props) {
  const { agregarTarea, cambioTarea, tarea,error } = props;

  return (
    <form action="" onSubmit={agregarTarea}>
      <TextField
        error={error}
        id="standard-basic"
        label="Ingrese su tarea"
        variant="standard"
        value={tarea}
        onChange={cambioTarea}
      />
      <Button variant="outlined" onClick={agregarTarea}>
        Agregar
      </Button>
    </form>
  );
}

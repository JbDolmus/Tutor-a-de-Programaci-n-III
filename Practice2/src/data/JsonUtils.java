package data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


public class JsonUtils<T> {
    private String filePath;
    private static final ObjectMapper mapper =
            new ObjectMapper().registerModule(new JavaTimeModule());

    public JsonUtils(String fp) {
        this.filePath = fp;
    }

    // Obtener todos los elementos como un Map
    public Map<String, T> getElements(Class<T> temp) throws IOException {
        File file = new File(this.filePath);
        if (!file.exists()) {
            return new HashMap<>();
        }
        return mapper.readValue(
                file,
                mapper.getTypeFactory().constructMapType(Map.class, String.class, temp)
        );
    }

    // Guardar un elemento
    public void save(T t, String name) throws IOException {
        Map<String, T> map = getElements((Class<T>) t.getClass());
        map.put(name, t); // Añadir o reemplazar el elemento
        mapper.writeValue(new File(filePath), map);
    }

    // Actualizar un elemento por nombre
    public void updateById(T updatedItem, String id) throws IOException {
        Map<String, T> map = getElements((Class<T>) updatedItem.getClass());
        if (map.containsKey(id)) {
            map.put(id, updatedItem); // Actualiza el elemento
            mapper.writeValue(new File(filePath), map);
        } else {
            throw new IllegalArgumentException("No se encontró un elemento con el id: " + id);
        }
    }

    // Eliminar un elemento por nombre
    public void deleteById(String id, Class<T> temp) throws IOException {
        Map<String, T> map = getElements(temp);
        if (map.remove(id) != null) { // Remueve el elemento si existe
            mapper.writeValue(new File(filePath), map);
        } else {
            throw new IllegalArgumentException("No se encontró un elemento con el id: " + id);
        }
    }
}

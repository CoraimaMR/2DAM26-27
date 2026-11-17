# Apuntes de AWS - Almacenamiento en Amazon S3

Estos apuntes resumen los conceptos clave, casos de uso, seguridad, transferencias y clases de almacenamiento de **Amazon Simple Storage Service (Amazon S3)** abordados en el cuestionario.

## 1\. Fundamentos y Escalabilidad de Amazon S3

* **Almacenamiento de objetos altamente escalable:** Amazon S3 permite almacenar cantidades prácticamente ilimitadas de datos (estructurados, semiestructurados y no estructurados) sin necesidad de aprovisionar capacidad por adelantado.
* **Modelo de pago por uso:** Es la opción más rentable cuando no se tiene certeza del volumen de almacenamiento futuro, pagando únicamente por el espacio utilizado.
* **Accesibilidad web:** Los objetos almacenados en S3 son accesibles a través de Internet mediante protocolos HTTP/HTTPS, lo que lo convierte en la solución ideal para alojar contenido multimedia (como videos) consumido desde sitios web externos.

## 2\. Alojamiento Web Estático en Amazon S3

Amazon S3 permite alojar sitios web estáticos completos sin necesidad de administrar servidores web tradicionales.

* **Contenido admitido:**

  * Archivos HTML y hojas de estilo CSS.
  * Archivos de imagen, video y sonido.
  * **Scripts del lado del cliente** (como JavaScript, que se ejecutan directamente en el navegador del usuario).
* **Contenido NO admitido:**

  * Scripts del lado del servidor (como PHP, Python o Node.js ejecutados en backend).
  * Motores de bases de datos.

## 3\. Seguridad y Gestión de Versiones

### Protección de Información Confidencial (Cifrado)

Para proteger datos sensibles en Amazon S3 se aplican capas de seguridad tanto en tránsito como en reposo:

1. **Cifrado del lado del servidor (SSE):** Debe habilitarse en el bucket **antes** de cargar la información confidencial para garantizar que todos los objetos nuevos se cifren automáticamente al guardarse.
2. **Cifrado del lado del cliente:** Permite cifrar los datos localmente antes de enviarlos a AWS, asegurando su protección de extremo a extremo durante el tránsito.

### Control de Versiones (S3 Versioning)

Al activar el control de versiones en un bucket utilizado como repositorio compartido de archivos, S3 proporciona:

* **Historial de cambios:** Mantiene múltiples versiones de un mismo archivo a medida que se sobrescribe o actualiza.
* **Protección contra borrados:** Permite **recuperar archivos eliminados** accidentalmente, ya que en lugar de borrar el dato permanentemente, S3 inserta un marcador de eliminación.

## 4\. Optimización de Transferencia de Archivos

Dependiendo de las condiciones de red y la distancia geográfica, AWS ofrece mecanismos específicos para subir archivos a S3:

* **Amazon S3 Transfer Acceleration:** Utiliza la red global de ubicaciones periféricas (*Edge Locations*) de **Amazon CloudFront** para acelerar la transferencia de archivos a largas distancias hacia un bucket de S3 mediante rutas de red optimizadas.
* **Cargas Multiparte (*Multipart Uploads*):** Divide archivos grandes (recomendado a partir de 100 MB, como videos de 100 a 700 MB) en partes más pequeñas que se suben en paralelo. Si una conexión a internet poco fiable se interrumpe, solo se reintenta la parte afectada sin reiniciar toda la carga.

## 5\. Clases de Almacenamiento y Políticas de Ciclo de Vida

Elegir la clase de almacenamiento adecuada y automatizar transiciones mediante **políticas de ciclo de vida** es clave para la optimización de costos:

|Clase de Almacenamiento|Caso de Uso Principal|Disponibilidad / Redundancia|Tiempo de Recuperación|
|-|-|-|-|
|**S3 Standard**|Datos de acceso frecuente (ej. casos activos durante los primeros 30 días).|Alta disponibilidad ($\\ge 3$ AZ).|Inmediato (milisegundos).|
|**S3 Standard - Acceso poco frecuente (S3 Standard-IA)**|Datos que se consultan con menor frecuencia pero requieren **acceso inmediato** si se necesitan (ej. casos cerrados en los últimos 12 meses).|Alta disponibilidad ($\\ge 3$ AZ).|Inmediato (milisegundos).|
|**S3 One Zone - Acceso poco frecuente (S3 One Zone-IA)**|Copias de respaldo secundarias o datos que **se pueden volver a crear fácilmente** en las instalaciones. Menor costo que Standard-IA.|Una única Zona de Disponibilidad (1 AZ).|Inmediato (milisegundos).|
|**S3 Glacier Deep Archive**|Retención de archivos a muy largo plazo por **cumplimiento normativo/reglamentario** (ej. 7 años) con acceso muy esporádico (1 o 2 veces al año). **Menor costo de S3**.|Alta durabilidad ($\\ge 3$ AZ).|Horas (típicamente de 12 a 48 horas).|




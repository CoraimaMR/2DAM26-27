# Apuntes de AWS - Módulo 2: Diseño y Arquitectura en la Nube

Estos apuntes resumen los conceptos clave, mejores prácticas y principios arquitectónicos de Amazon Web Services (AWS) abordados en el Módulo 2.

\---

## 1\. Definición de Arquitectura en la Nube

La **arquitectura en la nube** consiste en la aplicación de las características propias de la nube (elasticidad, escalabilidad, alta disponibilidad, pago por uso) a una solución que utiliza servicios y recursos en la nube para cumplir de manera óptima con los requisitos técnicos y empresariales de una organización.

\---

## 2\. El Marco de AWS Well-Architected

El *AWS Well-Architected Framework* proporciona un conjunto de prácticas recomendadas para diseñar y operar sistemas fiables, seguros, eficientes y rentables en la nube. Se basa en **6 pilares fundamentales**:

1. **Excelencia operativa:** Capacidad de ejecutar y monitorizar sistemas para ofrecer valor empresarial y mejorar continuamente los procesos y procedimientos.

   * *Acciones clave:* Revisar y mejorar procesos en un ciclo continuo; aplicar principios de ingeniería de software a la infraestructura (Infraestructura como Código - IaC).
2. **Seguridad:** Protección de la información, los sistemas y los activos mediante evaluaciones de riesgos y estrategias de mitigación.
3. **Fiabilidad:** Capacidad de un sistema para recuperarse de interrupciones de la infraestructura o del servicio, adquirir recursos dinámicamente para satisfacer la demanda y mitigar interrupciones.
4. **Rendimiento eficiente:** Uso eficiente de los recursos informáticos para cumplir con los requisitos del sistema y mantener esa eficiencia a medida que cambia la demanda.
5. **Optimización de costos:** Capacidad de ejecutar sistemas para ofrecer valor empresarial al precio más bajo posible.

   * *Práctica recomendada:* Aprovisionar únicamente los servidores necesarios y detener o apagar los servicios cuando no se estén utilizando (modelo de pago por uso).
6. **Sostenibilidad:** Minimización del impacto ambiental de la ejecución de cargas de trabajo en la nube.

\---

## 3\. Prácticas Recomendadas de Diseño y Automatización

### Desacoplamiento de Niveles

* Cuando una aplicación cuenta con múltiples niveles (por ejemplo, un nivel web *frontend* de varios servidores y un nivel de aplicación *backend* de varios servidores), no deben conectarse directamente entre sí de forma rígida.
* **Solución recomendada:** Utilizar **Elastic Load Balancing (ELB)** entre ambos niveles para distribuir el tráfico, evitar cuellos de botella, mejorar la tolerancia a fallos y permitir el escalado independiente.

### Gestión Automatizada de Errores

* En lugar de depender de la intervención manual de un administrador tras recibir una alerta, los sistemas en la nube deben autorrepararse.
* **Flujo recomendado:** **Amazon CloudWatch** detecta de forma automática un fallo o anomalía en el sistema y dispara un proceso de **automatización** (como AWS Auto Scaling o AWS Systems Manager) para aprovisionar un nuevo servidor sin intervención humana.

\---

## 4\. Almacenamiento, Entrega de Contenido y Seguridad en S3

### Distribución Global de Contenido Estático

* Si se almacenan datos de solo lectura en **Amazon S3** y los usuarios están distribuidos por todo el mundo:

  * **Solución recomendada:** Crear un único bucket en la región de AWS más cercana a la sede principal de la empresa y distribuir el contenido a los usuarios globales a través de **Amazon CloudFront** (Red de Entrega de Contenido - CDN), aprovechando sus ubicaciones de borde (*Edge Locations*) para reducir la latencia sin necesidad de replicar buckets manualmente.

### Concesión de Acceso Temporal y Seguro

* Para otorgar acceso puntual a un objeto privado de gran tamaño en Amazon S3 a un usuario externo (por ejemplo, un consultor durante 24 horas) sin comprometer la seguridad ni hacer público el bucket:

  * **Solución recomendada:** Generar una **URL prefirmada (*Presigned URL*)** con un tiempo de caducidad configurado (ej. 24 horas). Esto cumple con el principio de mínimo privilegio y evita crear cuentas de IAM innecesarias.

\---

## 5\. Infraestructura Global: Regiones vs. Zonas de Disponibilidad (AZ)

### Consideraciones para elegir una Región de AWS

Una Región es una ubicación física en el mundo donde AWS agrupa centros de datos. Su elección depende principalmente de:

1. **Reducción de la latencia:** Elegir una región geográficamente cercana a los usuarios finales para mejorar los tiempos de respuesta.
2. **Cumplimiento normativo:** Asegurar el cumplimiento de las leyes y reglamentos locales sobre soberanía y privacidad de los datos.

### Consideraciones para elegir Zonas de Disponibilidad (AZ)

Una Zona de Disponibilidad consta de uno o más centros de datos independientes dentro de una misma región, conectados mediante enlaces de baja latencia pero aislados físicamente. Su uso (especialmente el despliegue Multi-AZ) responde a:

1. **Protección contra desastres naturales localizados:** Al estar separadas físicamente, un evento como una inundación o corte eléctrico en una AZ no afecta a las demás.
2. **Resiliencia de las aplicaciones:** Garantiza la alta disponibilidad y continuidad del negocio en caso de errores de hardware o fallos del sistema en una ubicación específica.


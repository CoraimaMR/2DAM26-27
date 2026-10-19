# Apuntes de Estudio: Seguridad en AWS (Módulo 3)

## 1. Pilar de Seguridad del Marco Well-Architected
- **Principio fundamental:** Aplicar la seguridad en todas las capas de una arquitectura.
- Implementa un enfoque de defensa en profundidad que cubre el perímetro de red, VPC, subredes, instancias, sistemas operativos y aplicaciones.

## 2. Modelo de Responsabilidad Compartida
- **AWS es responsable de la seguridad DE la nube:**
  - Protección de la infraestructura global (hardware, software, redes).
  - Seguridad física e instalaciones de los centros de datos.
- **El cliente es responsable de la seguridad EN la nube:**
  - Administración y protección de los datos de usuarios.
  - Configuración de grupos de seguridad y firewalls basados en host.
  - Gestión de sistemas operativos, parches y accesos de usuarios.

## 3. Principio de Mínimo Privilegio
- **Definición:** Otorgar únicamente los permisos estrictamente necesarios para realizar una tarea específica.
- **Puntos clave:**
  - Elaboración de políticas de seguridad que limiten el acceso a tareas específicas.
  - Concesión de acceso según sea necesario (bajo demanda).

## 4. AWS Identity and Access Management (IAM)
- **Acceso Granular:** Permite conceder a las entidades principales permisos detallados sobre los recursos.
- **Roles de IAM:**
  - No tienen credenciales fijas o permanentes; proporcionan **credenciales temporales**.
  - Pueden ser asumidos por usuarios, aplicaciones y servicios de AWS.
- **Prácticas recomendadas para el usuario raíz (Root User):**
  - Crear un usuario administrador para las tareas cotidianas en lugar de usar el usuario raíz.
  - Proteger el usuario raíz habilitando MFA (Autenticación Multifactor).
- **Tipos de políticas:**
  - **Políticas basadas en identidad:** Se asocian a un usuario, grupo o rol.
  - **Políticas basadas en recursos:** Se adjuntan directamente a un recurso (ej. bucket de S3).

## 5. Estructura y Lógica de Evaluación de Políticas
- **Estructura:**
  - **Statement (Enunciado):** Contiene los elementos que en conjunto definen lo que está permitido o denegado.
  - **Effect (Efecto):** Elemento que especifica si se debe permitir (`Allow`) o denegar (`Deny`) la solicitud.
- **Lógica de evaluación:**
  - AWS comprueba primero si existe una **denegación explícita** (`Deny`). Si existe, anula cualquier permiso explícito.
  - Si no hay denegación explícita, evalúa si existe un permiso explícito (`Allow`).
  - Si no hay ningún permiso explícito, la solicitud se deniega por defecto (denegación implícita).
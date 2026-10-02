# 🍽️ RestoControl - Sistema de Gestión de Pedidos

**RestoControl** es una aplicación de escritorio desarrollada en Java utilizando el patrón de arquitectura **MVC (Modelo-Vista-Controlador)**. Este proyecto fue diseñado para facilitar el registro, consulta y actualización de estados de pedidos en un restaurante, asegurando un código limpio y escalable.

## 🚀 Funcionalidades Principales

*   **Registro de Pedidos:** Permite ingresar nuevos pedidos asignando un ID único, nombre del cliente, número de mesa, detalle del plato y valor total.
*   **Consulta de Pedidos:** Búsqueda rápida de pedidos mediante su ID, mostrando un resumen detallado del consumo en una ventana emergente.
*   **Actualización de Estado (Nuevo Incremento):** Posibilidad de cambiar el estado de un pedido a *"En preparación"*, *"Entregado"* o *"Cancelado"*, con actualización visual de colores según el estado.

## 🛠️ Tecnologías y Herramientas Usadas

*   **Lenguaje:** Java
*   **Interfaz Gráfica:** Java Swing
*   **IDE:** Apache NetBeans
*   **Control de Versiones:** Git y GitHub
*   **Gestión de Proyecto (Metodología Ágil):** Trello

## 📂 Estructura del Proyecto (MVC)

El proyecto está dividido estrictamente en tres paquetes principales para separar los datos de la interfaz visual:

*   **`Model/` (Modelo):** Contiene la clase `Pedido.java` con los atributos (ID, mesa, cliente, detalle, estado, fecha) y métodos encapsulados.
*   **`View/` (Vista):** Contiene la clase `FrmPedido.java`, encargada de toda la interfaz gráfica (JFrame, paneles, botones) y la interacción con el usuario.
*   **`Controller/` (Controlador):** Contiene la clase `PedidoController.java`, que actúa como puente, capturando los clics de los botones de la vista y procesando la lógica con el modelo.

## 👥 Equipo de Desarrollo

*   **Andrés Felipe Morales Pretel**


## ⚙️ Cómo clonar y ejecutar el proyecto

1. Abre tu terminal de Git y clona este repositorio:
   ```bash
   git clone [https://github.com/TU-USUARIO/TU-REPOSITORIO.git](https://github.com/TU-USUARIO/TU-REPOSITORIO.git)

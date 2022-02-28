-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-02-2022 a las 20:40:40
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdtiendaonline`
--
CREATE DATABASE IF NOT EXISTS `bdtiendaonline` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `bdtiendaonline`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `idCategoria` int(5) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `imagen` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`idCategoria`, `nombre`, `imagen`) VALUES
(1, 'Bebidas con alcohol', 'bebidas.jpg'),
(2, 'Perfumería', 'perfumeria.jpg'),
(3, 'Repostería', 'reposteria.jpg'),
(10, 'CategoriaPruebaJuanmi', 'rutaImagen.jpg'),
(12, 'CategoriaPruebaJuanmi', 'rutaImagen.jpg'),
(52, 'Modificado', 'imagenModificada.jpg'),
(54, 'Modificado', 'imagen.jpg'),
(56, 'CategoriaPruebaJuanmi', 'rutaImagen.jpg'),
(57, 'CategoriaPruebaJuanmi', 'rutaImagen.jpg'),
(58, 'CategoriaPruebaJuanmi', 'rutaImagen.jpg'),
(60, 'CategoriaPruebaJuanmi', 'rutaImagen.jpg'),
(61, 'CategoriaPruebaJuanmi', 'rutaImagen.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleventas`
--

CREATE TABLE `detalleventas` (
  `codigoventa` int(10) NOT NULL,
  `codigoproducto` int(9) NOT NULL,
  `pvp` decimal(10,2) NOT NULL,
  `cantidad` int(4) NOT NULL,
  `pctDescuento` decimal(4,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detalleventas`
--

INSERT INTO `detalleventas` (`codigoventa`, `codigoproducto`, `pvp`, `cantidad`, `pctDescuento`) VALUES
(1, 1, '3.20', 3, '5.00'),
(1, 2, '11.20', 3, '5.00'),
(1, 3, '10.00', 3, '5.00'),
(1, 6, '21.50', 10, NULL),
(1, 8, '6.35', 10, NULL),
(1, 9, '1.55', 10, NULL),
(1, 10, '11.00', 10, NULL),
(1, 11, '25.00', 10, NULL),
(2, 6, '15.00', 5, '10.00'),
(2, 7, '15.00', 2, '10.00'),
(30, 1, '3.50', 1, '10.00'),
(40, 1, '3.50', 1, '10.00'),
(41, 1, '3.50', 1, '10.00'),
(42, 1, '3.50', 1, '10.00'),
(48, 4, '3.50', 1, '10.00'),
(48, 5, '3.50', 1, NULL),
(49, 4, '2.70', 1, '0.00'),
(49, 5, '4.60', 1, NULL),
(50, 4, '2.70', 1, '0.00'),
(50, 5, '4.60', 1, NULL),
(51, 4, '2.70', 1, '0.00'),
(51, 5, '4.60', 1, NULL),
(52, 4, '2.70', 1, '0.00'),
(52, 5, '4.60', 1, NULL),
(53, 4, '2.70', 1, '0.00'),
(53, 5, '4.60', 1, NULL),
(54, 4, '2.70', 1, '0.00'),
(54, 5, '4.60', 1, NULL),
(55, 4, '2.70', 1, '0.00'),
(55, 5, '4.60', 1, NULL),
(56, 4, '2.70', 1, '0.00'),
(56, 5, '4.60', 1, NULL),
(57, 4, '2.70', 1, '0.00'),
(57, 5, '4.60', 1, NULL),
(58, 4, '2.70', 1, '0.00'),
(58, 5, '4.60', 1, NULL),
(59, 4, '2.70', 1, '0.00'),
(59, 5, '4.60', 1, NULL),
(60, 4, '2.70', 1, '0.00'),
(60, 5, '4.60', 1, NULL),
(61, 6, '2.70', 1, '0.00'),
(61, 7, '4.60', 1, NULL),
(62, 6, '2.70', 1, '0.00'),
(62, 7, '4.60', 1, NULL),
(63, 6, '2.70', 1, '0.00'),
(63, 7, '4.60', 1, NULL),
(64, 6, '2.70', 1, '0.00'),
(64, 7, '4.60', 1, NULL),
(65, 6, '2.70', 1, '0.00'),
(65, 7, '4.60', 1, NULL),
(66, 6, '2.70', 1, '0.00'),
(66, 7, '4.60', 1, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `idProducto` int(10) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `precio` decimal(10,2) NOT NULL,
  `imagen` varchar(50) NOT NULL,
  `categoria` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`idProducto`, `nombre`, `descripcion`, `precio`, `imagen`, `categoria`) VALUES
(1, 'Vino tinto Rioja', 'Vino D.O. Rioja tinto. Viña Espolón', '3.20', '1.jpg', 1),
(2, 'Vino tinto El Coto', 'Estuche 3 Botellas El Coto Tinto 2017', '11.20', '2.jpg', 1),
(3, 'Vino tio Pepe', 'Tio Pepe Fino', '6.50', '3.jpg', 1),
(4, 'Sauvignon', 'Viñas Del Vero Cabernet Sauvignon-Merlot Tinto 2020', '4.49', '4.jpg', 1),
(5, 'Porto Cruz Tinto', 'Porto Cruz Tinto', '5.99', '5.jpg', 1),
(6, 'Faustino Tinto 2010', 'Faustino Tinto 2010', '21.50', '6.jpg', 1),
(7, 'BlancoReserva', 'Pure The Winery Sin Azúcar Blancoeserva 2016', '34.60', '9.jpg', 1),
(8, 'Viña Sol Blanco 2020', 'Viña Sol Blanco 2020', '6.35', '8.jpg', 1),
(9, 'Tinto RGredos', 'Pradorey Finca La Mina Tinto RGredos brik 1 l.', '1.55', '10.jpg', 1),
(10, 'Vino tinto de mesa', 'Vino tinto de mesa Cumbre de ', '11.00', '7.jpg', 1),
(11, 'Perfume1', 'Perfume1', '25.00', '11.jpg', 2),
(15, 'Producto', 'MODIFICADO descripcion producto prueba Juanmy', '28.16', 'prueba.jpg', 2),
(17, 'Producto17', 'a', '17.28', 'imagen.jpg', 2),
(19, 'Producto19', 'Descripcion19', '18.28', 'imagen.jpg', 2),
(20, 'Producto19', 'Descripcion19', '18.28', 'imagen.jpg', 2),
(21, 'Producto19', 'Descripcion19', '18.28', 'imagen.jpg', 2),
(22, 'ProductoModificado', 'DescripcionModificada producto prueba Juanmy', '28.16', 'prueba.jpg', 2),
(23, 'ProductoModificado', 'DescripcionModificada producto prueba Juanmy', '28.16', 'prueba.jpg', 2),
(24, 'ProductoModificado', 'DescripcionModificada producto prueba Juanmy', '28.16', 'prueba.jpg', 2),
(25, 'PruebaBautista', 'Descripcionbautista', '18.28', 'imagen.jpg', 2),
(26, 'PruebaBautista', 'Descripcionbautista', '18.28', 'imagen.jpg', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `idUsuario` int(10) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `localidad` varchar(100) NOT NULL,
  `perfil` varchar(20) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `claveusu` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idUsuario`, `apellidos`, `nombre`, `localidad`, `perfil`, `correo`, `claveusu`) VALUES
(1, 'Lopez Lara', 'Miguel', 'Madrid', 'Admin', 'miguel@correo.es', '1234'),
(2, 'Casas Ramos', 'Ana', 'Toledo', 'Cliente', 'ana@corre0o.es', '1234'),
(3, 'Hurtado Montejano', 'Juan', 'Tomelloso', 'Admin', 'jmhurtadomontejano@gmail.com', '1234'),
(9, 'Lara', 'Pepe', 'Tomelloso', 'Cliente', 'pepe@correo.es', '1234'),
(11, 'Prueba', 'Pepe', 'Tomelloso', 'Cliente', 'pepe4@correo.es', '1234'),
(14, 'Lara', 'Pepe', 'Tomelloso', 'Cliente', 'pepedsf24753@correo.es', '1234'),
(33, 'Ponce', 'Maria', 'Tomelloso', 'Cliente', 'maria@correo.es', '1234'),
(56, 'Prueba', 'Pepe', 'Tomelloso', 'Cliente', 'pepwde4@correo.es', '1234'),
(58, 'Prueba', 'Pepe', 'Tomelloso', 'Cliente', 'pepsdfsdf4@correo.es', '1234'),
(77, 'Ponce', 'Maria', 'Tomelloso', 'Cliente', 'mari2@correo.es', '1234'),
(79, 'Ponce', 'Maria', 'Tomelloso', 'Cliente', 'marsdfi2@correo.es', '1234'),
(80, 'Prueba', 'Pepe', 'Tomelloso', 'Cliente', 'pepsdf4@correo.es', '1234'),
(81, 'Ponce', 'Maria', 'Tomelloso', 'Cliente', 'emafsdil@gmail.com', '1234'),
(82, 'García', 'Pablo', 'Argamasilla de Alba', 'Cliente', 'pablo@gmail.com', '1234'),
(83, 'Montes', 'Pablo', 'Argamasilla de Alba', 'Cliente', 'pablo2@gmail.com', '1234');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `idVenta` int(10) NOT NULL,
  `cliente` int(10) NOT NULL,
  `fecha` date DEFAULT NULL,
  `estado` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`idVenta`, `cliente`, `fecha`, `estado`) VALUES
(1, 1, '2021-12-10', ''),
(2, 2, '2021-12-20', ''),
(3, 2, '2022-01-19', ''),
(10, 3, '1970-01-01', ''),
(11, 1, '2021-01-04', ''),
(13, 2, '1970-01-01', ''),
(14, 1, '1970-01-01', ''),
(16, 3, '2021-01-04', ''),
(17, 3, '2021-01-04', ''),
(19, 3, '2021-01-04', ''),
(20, 1, '2021-01-04', ''),
(22, 3, '2021-01-04', ''),
(23, 3, '2021-01-04', ''),
(30, 2, '2021-01-04', ''),
(40, 3, '2021-01-04', ''),
(41, 3, '2021-01-04', ''),
(42, 1, '2021-01-04', ''),
(48, 2, '2021-01-04', ''),
(49, 3, '2021-01-04', ''),
(50, 3, '2021-01-04', ''),
(51, 3, '2021-01-04', ''),
(52, 3, '2021-01-04', ''),
(53, 3, '2021-01-04', ''),
(54, 3, '2021-01-04', ''),
(55, 3, '2021-01-04', ''),
(56, 3, '2021-01-04', ''),
(57, 3, '2021-01-04', ''),
(58, 3, '2021-01-04', ''),
(59, 3, '2021-01-04', ''),
(60, 3, '2021-01-04', ''),
(61, 3, '2021-01-04', ''),
(62, 3, '2021-01-04', ''),
(63, 3, '2021-01-04', ''),
(64, 3, '2021-01-04', ''),
(65, 3, '2021-01-04', ''),
(66, 3, '2021-01-04', ''),
(105, 1, '2021-01-04', ''),
(106, 1, '2021-01-04', ''),
(107, 1, '2021-01-04', '');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`idCategoria`);

--
-- Indices de la tabla `detalleventas`
--
ALTER TABLE `detalleventas`
  ADD PRIMARY KEY (`codigoventa`,`codigoproducto`),
  ADD KEY `FKPRO` (`codigoproducto`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`idProducto`),
  ADD KEY `fkcateg` (`categoria`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`idUsuario`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`idVenta`),
  ADD KEY `FKCLI` (`cliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `idUsuario` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalleventas`
--
ALTER TABLE `detalleventas`
  ADD CONSTRAINT `FKPRO` FOREIGN KEY (`codigoproducto`) REFERENCES `productos` (`idProducto`),
  ADD CONSTRAINT `FKVENTA` FOREIGN KEY (`codigoventa`) REFERENCES `ventas` (`idVenta`);

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `fkcateg` FOREIGN KEY (`categoria`) REFERENCES `categorias` (`idCategoria`);

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `FKCLI` FOREIGN KEY (`cliente`) REFERENCES `usuarios` (`idUsuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

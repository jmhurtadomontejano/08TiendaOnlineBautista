-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-01-2022 a las 17:48:52
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
(3, 'Repostería', 'reposteria.jpg');

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
(1, 3, '10.00', 3, '5.00');

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
(11, 'Perfume1', 'Perfume1', '25.00', '11.jpg', 2);

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
(3, 'Hurtado Montejano', 'Juan', 'Tomelloso', 'Admin', 'jmhurtadomontejano@gmail.com', '1234');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `idVenta` int(10) NOT NULL,
  `cliente` int(10) NOT NULL,
  `fecha` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`idVenta`, `cliente`, `fecha`) VALUES
(1, 1, '2021-12-10'),
(2, 2, '2021-12-20'),
(3, 2, '2022-01-19');

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
  MODIFY `idUsuario` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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

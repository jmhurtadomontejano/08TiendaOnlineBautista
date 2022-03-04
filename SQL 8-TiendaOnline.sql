-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-03-2022 a las 17:17:37
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
(1, 'Ordenadores', 'ordenadores.jpg'),
(2, 'Monitores', 'monitores.jpg'),
(3, 'Teclados', 'teclados.jpg'),
(59, 'Discos Duros', 'discoDuros.jpg');

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
(41, 101, '898.00', 1, NULL),
(54, 102, '2049.00', 1, NULL),
(55, 101, '3592.00', 4, NULL),
(56, 301, '83.00', 1, NULL),
(57, 101, '898.00', 1, NULL),
(58, 101, '1796.00', 2, NULL);

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
  `categoria` int(5) NOT NULL,
  `disponible` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`idProducto`, `nombre`, `descripcion`, `precio`, `imagen`, `categoria`, `disponible`) VALUES
(101, 'Asus TUF Gaming F15 FX506HCB-HN200', ' Intel Core i5-11400H/16GB/512GB SSD/RTX 3050/15.6\"', '898.00', 'Asus-TUF-GAMING F15.jpg', 1, 'Si'),
(102, 'MSI Pulse GL66 12UGK-471XES', ' Intel Core i7-12700H/32GB/512GB SSD/RTX 3070/15.6\"', '2049.00', 'MSI Pulse GL66.jpg', 1, 'Si'),
(103, 'Acer Nitro 5 AN515-45-R19D ', 'AMD Ryzen 5 5600H/16GB/512GB SSD/RTX 3060/15.6\"', '1199.00', 'Acer Nitro 5.jpg', 1, 'Si'),
(201, 'Monitor HP X32c 31.5\" ', 'LED FullHD 165Hz FreeSync Premium Curva', '304.40', 'MonitorHPx32c.jpg', 2, 'Si'),
(202, 'MSI Optix G272 27\" ', 'LED IPS FullHD 144Hz G-Sync Compatible', '258.99', 'MonitorMsi27.jpg', 2, 'Si'),
(203, 'Monitor Acer ED270X 27\"', 'LED FullHD 240Hz FreeSync Curva', '270.14', 'MonitorAcer27.jpg', 2, 'Si'),
(301, 'Teclado Mecanico Inalámbrico Logitech', 'POP', '83.00', 'tecladoLogitechPop.jpg', 3, 'No'),
(302, 'Teclado Tempest K20 Beast', 'Teclado MecÃ¡nico Gaming RGB TKL Negro', '50.99', 'tecladoTempest.jpg', 3, 'Si'),
(303, 'Teclado para Portátil Acer ', 'Para equipos Extensa 5735Z-007SP/Aspire 5737/5735/5355', '30.63', 'TecladoParaPortatilAcer.jpg', 3, 'Si'),
(401, 'Disco Duro Samsung 980 M.2', 'SSD 500GB PCIe NVMe M.2', '118.50', 'Samsung980.jpg', 59, 'Si');

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
(4, 'Cutillas', 'Evelyn', 'Colomalcazar', 'Cliente', 'evelyn@gmail.com', '1234'),
(5, 'Polo', 'Marco', 'Roma', 'Cliente', 'marco@gmail.com', '1234'),
(7, 'sas', 'Juan', 'sasas', 'Cliente', 'jmhurtadomontejano2@gmail.com', '1234'),
(9, 'Juan', 'Juan', 'Tome', 'Cliente', 'jmhurtadomontejano3@gmail.com', '1234');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `idVenta` int(10) NOT NULL,
  `cliente` int(10) NOT NULL,
  `fecha` date DEFAULT NULL,
  `estado` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`idVenta`, `cliente`, `fecha`, `estado`) VALUES
(41, 4, '2022-03-02', 'Pagado'),
(50, 4, '2022-03-02', 'Pagado'),
(51, 4, '2022-03-02', 'Pagado'),
(52, 4, '2022-03-02', 'Pagado'),
(53, 4, '2022-03-02', 'Pagado'),
(54, 4, '2022-03-02', 'Pagado'),
(55, 3, '2022-03-02', 'Pagado'),
(56, 3, '2022-03-02', 'Pagado'),
(57, 3, '2022-03-02', 'Pagado'),
(58, 3, '2022-03-02', 'Pagado');

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
  MODIFY `idUsuario` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `idVenta` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalleventas`
--
ALTER TABLE `detalleventas`
  ADD CONSTRAINT `FKPRO` FOREIGN KEY (`codigoproducto`) REFERENCES `productos` (`idProducto`),
  ADD CONSTRAINT `FKVEN` FOREIGN KEY (`codigoventa`) REFERENCES `ventas` (`idVenta`);

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

delimiter $$

CREATE TABLE `comprobantepago` (
  `idComprobantePago` int(11) NOT NULL AUTO_INCREMENT,
  `NSerie` int(11) DEFAULT NULL,
  `NDocumento` int(11) DEFAULT NULL,
  `TipoDocumento` int(11) DEFAULT NULL,
  `Fecha` datetime DEFAULT NULL,
  `FechaVence` datetime DEFAULT NULL,
  `Moneda` int(11) DEFAULT NULL,
  `DetraccionPorcentaje` decimal(18,2) DEFAULT NULL,
  `IGV` decimal(18,2) DEFAULT NULL,
  `TotalPercepcion` decimal(18,2) DEFAULT NULL,
  `TotalNoGravado` decimal(18,2) DEFAULT NULL,
  `TotalRenta` decimal(18,2) DEFAULT NULL,
  `TotalDescuento` decimal(18,2) DEFAULT NULL,
  `Total` decimal(18,2) DEFAULT NULL,
  `TipoCompra` decimal(18,2) DEFAULT NULL,
  `OrdendeCompra` int(11) DEFAULT NULL,
  `Proveedor` int(11) DEFAULT NULL,
  `Contabilidad` int(11) DEFAULT NULL,
  `Autogenerado` varchar(45) DEFAULT NULL,
  `Almacen` int(11) DEFAULT NULL,
  `Activo` int(11) DEFAULT NULL,
  `FechaRegistro` datetime DEFAULT NULL,
  `UsuarioModificacion` varchar(45) DEFAULT NULL,
  `FechaModificacion` datetime DEFAULT NULL,
  PRIMARY KEY (`idComprobantePago`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8$$



delimiter $$

CREATE TABLE `det_comprobantepago` (
  `iddet_comprobantepago` int(11) NOT NULL AUTO_INCREMENT,
  `Cantidad` decimal(18,2) DEFAULT NULL,
  `VVenta` decimal(18,2) DEFAULT NULL,
  `PUnitario` decimal(18,2) DEFAULT NULL,
  `ImporteCosto` decimal(18,2) DEFAULT NULL,
  `CostoProveedor` decimal(18,2) DEFAULT NULL,
  `Percepcion` int(11) DEFAULT NULL,
  `ExoneradoIGV` int(11) DEFAULT NULL,
  `Gasto` decimal(18,2) DEFAULT NULL,
  `Producto` varchar(20) DEFAULT NULL,
  `conNC` int(11) DEFAULT NULL,
  `ComprobantePago` int(11) DEFAULT NULL,
  `DNotaIngreso` int(11) DEFAULT NULL,
  `OrdendeCompra` int(11) DEFAULT NULL,
  `Activo` int(11) DEFAULT NULL,
  `FechaRegistro` datetime DEFAULT NULL,
  `UsuarioModificacion` varchar(45) DEFAULT NULL,
  `FechaModificacion` datetime DEFAULT NULL,
  PRIMARY KEY (`iddet_comprobantepago`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8$$



-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_InsertComprobantePago`(in prmxml text)
BEGIN 
DECLARE i INT ;
DECLARE coun INT;
declare idcomprobante int;
SET i =1;
SET coun = ExtractValue(prmxml, 'count(/root/Comprobante/Dcomprobante/Cantidad)');

insert into comprobantepago(NSerie,NDocumento,TipoDocumento,Fecha,FechaVence,Moneda,DetraccionPorcentaje,IGV,
TotalPercepcion,TotalNoGravado,TotalRenta,TotalDescuento,Total,TipoCompra,OrdendeCompra,Proveedor,Contabilidad,
Almacen,Activo,FechaRegistro)
SELECT 
CONVERT(ExtractValue(prmxml, '//Comprobante[1]/NSerie'), SIGNED ),
CONVERT(ExtractValue(prmxml, '//Comprobante[1]/NDocumento'), SIGNED ),
CONVERT(ExtractValue(prmxml, '//Comprobante[1]/TipoDocumento'), SIGNED ),
CONVERT(ExtractValue(prmxml, '//Comprobante[1]/Fecha'), DATETIME  ),
CONVERT(ExtractValue(prmxml, '//Comprobante[1]/FechaVence'), DATETIME  ),
CONVERT(ExtractValue(prmxml, '//Comprobante[1]/Moneda'), SIGNED ),
CONVERT(ExtractValue(prmxml, '//Comprobante[1]/DetraccionPorcentaje'), DECIMAL(18,2)  ),
CONVERT(ExtractValue(prmxml, '//Comprobante[1]/IGV'), DECIMAL(18,2)  ),
CONVERT(ExtractValue(prmxml, '//Comprobante[1]/TotalPercepcion'), DECIMAL(18,2)  ),
CONVERT(ExtractValue(prmxml, '//Comprobante[1]/TotalNoGravado'), DECIMAL(18,2)  ),
CONVERT(ExtractValue(prmxml, '//Comprobante[1]/TotalRenta'), DECIMAL(18,2)  ),
CONVERT(ExtractValue(prmxml, '//Comprobante[1]/TotalDescuento'), DECIMAL(18,2)  ),
CONVERT(ExtractValue(prmxml, '//Comprobante[1]/Total'), DECIMAL(18,2)  ),
CONVERT(ExtractValue(prmxml, '//Comprobante[1]/TipoCompra'), DECIMAL(18,2)  ),
CONVERT(ExtractValue(prmxml, '//Comprobante[1]/OrdenCompra'), SIGNED ),
CONVERT(ExtractValue(prmxml, '//Comprobante[1]/Proveedor'), SIGNED ),
CONVERT(ExtractValue(prmxml, '//Comprobante[1]/Contabilidad'), SIGNED ),
CONVERT(ExtractValue(prmxml, '//Comprobante[1]/Almacen'), SIGNED ),
1,Now();

 
set idcomprobante=(select Max(idComprobantePago) from comprobantepago); -- @@identity;
-- select coun;

WHILE i <= coun DO


     INSERT INTO det_comprobantepago(Cantidad,
VVenta,
PUnitario,
ImporteCosto,
CostoProveedor,
Percepcion,
ExoneradoIGV,
Gasto,
Producto,
conNC,
ComprobantePago,
DNotaIngreso,
OrdendeCompra,
Activo,
FechaRegistro)
    SELECT CONVERT(ExtractValue(prmxml, '//Comprobante[1]/Dcomprobante[$i]/Cantidad'),DECIMAL(18,2) ),
    CONVERT(ExtractValue(prmxml, '//Comprobante[1]/Dcomprobante[$i]/Vventa'),DECIMAL(18,2) ),
    CONVERT(ExtractValue(prmxml, '//Comprobante[1]/Dcomprobante[$i]/PUnitario'),DECIMAL(18,2) ),
    CONVERT(ExtractValue(prmxml, '//Comprobante[1]/Dcomprobante[$i]/ImporteCosto'),DECIMAL(18,2) ),
    CONVERT(ExtractValue(prmxml, '//Comprobante[1]/Dcomprobante[$i]/CostoProveedor'),DECIMAL(18,2) ),
    CONVERT(ExtractValue(prmxml, '//Comprobante[1]/Dcomprobante[$i]/Percepcion'),SIGNED ),
    CONVERT(ExtractValue(prmxml, '//Comprobante[1]/Dcomprobante[$i]/ExoneradoIGV'),SIGNED ),
    CONVERT(ExtractValue(prmxml, '//Comprobante[1]/Dcomprobante[$i]/Gasto'),DECIMAL(18,2) ),
    CONVERT(ExtractValue(prmxml, '//Comprobante[1]/Dcomprobante[$i]/Producto'),char(45) ),
    CONVERT(ExtractValue(prmxml, '//Comprobante[1]/Dcomprobante[$i]/conNC'),SIGNED ),
    idcomprobante,
    CONVERT(ExtractValue(prmxml, '//Comprobante[1]/Dcomprobante[$i]/DetalleNotaIngreso'),SIGNED ),
    CONVERT(ExtractValue(prmxml, '//Comprobante[1]/Dcomprobante[$i]/OrdenCompra'),SIGNED ),
    1,
    now();

    SET i = i+1;

 END WHILE;

END

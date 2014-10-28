-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ListarHojaTrabajoStock`(in prmsrtgrupo varchar(3),
IN prmstrmarca varchar(3))
BEGIN
select P.IDPRODUCTO, P.Descripcion ,sum(PSM.REPOSICION) as stock, sum(PSM.CANTIDAD) as stockminimo,
"" as backorder,sum(DR.CANTIDAD) as requerimiento
from producto P INNER JOIN productostockminimo PSM ON P.IDPRODUCTO=PSM.IDPRODUCTO
INNER JOIN drequerimiento DR ON P.IDPRODUCTO = DR.idproducto
where P.idgrupo like CONCAT('%',prmsrtgrupo,'%') and P.MARCA_IDMARCA like CONCAT('%',prmstrmarca,'%')
group by P.IDPRODUCTO, P.Descripcion ;
END




-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ListarGrupo`()
BEGIN
SELECT * FROM bd.grupo;
END



-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ListarMarca`()
BEGIN
SELECT * FROM bd.marca;
END


-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ListarHojaTrabajoStock`()
BEGIN
select P.IDPRODUCTO, P.Descripcion ,PSM.REPOSICION as stock, PSM.CANTIDAD as stockminimo,"" as backorder,DR.CANTIDAD as requerimiento
from producto P INNER JOIN productostockminimo PSM ON P.IDPRODUCTO=PSM.IDPRODUCTO
INNER JOIN drequerimiento DR ON P.IDPRODUCTO = DR.idproducto;

END

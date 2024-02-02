-- 20758 si lees del csv, 20744 si autogeneras la clave
SELECT count(*) FROM alojamientos.alojamientos;-- 20758 --> mi caso
SELECT count(*) FROM alojamientos.barrios; -- 221
SELECT count(*) FROM alojamientos.propietarios; -- 12449
SELECT count(*) FROM alojamientos.tiposhabitacion; -- 4
SELECT count(*) FROM alojamientos.distritos; -- 5

SELECT * FROM alojamientos.alojamientos WHERE id=2596;
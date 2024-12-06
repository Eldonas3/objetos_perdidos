from django.db import models

class TipoUsuario(models.Model):
    tipo = models.CharField(max_length=30)

    class meta:
        db_table = 'tipo_usuario'

    def __str__(self):
        return self.tipo

class Usuario(models.Model):
    nombre_usuario = models.CharField(max_length=255, unique=True)
    contrasena = models.CharField(max_length=30)
    correo = models.EmailField(max_length=100)
    no_control = models.CharField(max_length=20)
    tipo = models.ForeignKey(TipoUsuario, on_delete=models.CASCADE)

    class meta:
        db_table = 'usuario'

    def __str__(self):
        return self.nombre_usuario

class Objeto(models.Model):
    nombre = models.CharField(max_length=255)
    descripcion = models.TextField()
    url_imagen = models.URLField(max_length=512, null=True, blank=True)
    fecha_reporte = models.DateTimeField(auto_now_add=True)
    lugar_encontrado = models.CharField(max_length=100)
    entregado = models.BooleanField(default=False)
    solicitado = models.BooleanField(default=False)
    usuario_objeto = models.ForeignKey(Usuario, on_delete=models.CASCADE)

    class meta:
        db_table = 'objeto'

    def __str__(self):
        return self.nombre

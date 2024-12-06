from django.shortcuts import render
from rest_framework import viewsets
from .models import TipoUsuario, Usuario, Objeto
from .serializers import TipoUsuarioSerializer, UsuarioSerializer, ObjetoSerializer
from django_filters.rest_framework import DjangoFilterBackend
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
from django.contrib.auth.hashers import check_password
from rest_framework.authtoken.models import Token
from django.db import connection
import json

class TipoUsuarioViewSet(viewsets.ModelViewSet):
    queryset = TipoUsuario.objects.all()
    serializer_class = TipoUsuarioSerializer
    filter_backends = [DjangoFilterBackend]
    filterset_fields = ['tipo']

class UsuarioViewSet(viewsets.ModelViewSet):
    queryset = Usuario.objects.all()
    serializer_class = UsuarioSerializer
    filter_backends = [DjangoFilterBackend]
    filterset_fields = ['nombre_usuario','contrasena', 'correo', 'no_control','tipo']


class ObjetoViewSet(viewsets.ModelViewSet):
    queryset = Objeto.objects.all()
    serializer_class = ObjetoSerializer
    filter_backends = [DjangoFilterBackend]
    filterset_fields = ['nombre','descripcion','url_imagen','fecha_reporte','lugar_encontrado','entregado','solicitado', 'usuario_objeto']

@csrf_exempt
def login_view(request):
    if request.method == 'POST':
        try:
            # Parsear los datos enviados por el cliente
            data = json.loads(request.body)
            correo = data.get('correo')
            contrasena = data.get('contrasena')

            if not correo or not contrasena:
                return JsonResponse({"error": "Correo y contraseña son requeridos"}, status=400)

            # Consultar la base de datos para obtener el usuario
            with connection.cursor() as cursor:
                cursor.execute("""
                    SELECT u.id_usuario, u.contrasena, t.tipo 
                    FROM usuario u 
                    JOIN tipo_usuario t ON u.tipo = t.id_tipo_usuario 
                    WHERE u.correo = %s
                """, [correo])
                user_data = cursor.fetchone()

            if not user_data:
                return JsonResponse({"error": "Usuario no encontrado"}, status=404)

            user_id, stored_password, tipo_usuario = user_data

            # Validar la contraseña (texto plano en este caso)
            if contrasena != stored_password:
                return JsonResponse({"error": "Contraseña incorrecta"}, status=401)

            # Respuesta exitosa con información del usuario
            return JsonResponse({
                "user_id": user_id,
                "user_type": tipo_usuario,
                "message": "Autenticación exitosa"
            }, status=200)

        except Exception as e:
            return JsonResponse({"error": str(e)}, status=500)

    return JsonResponse({"error": "Método no permitido"}, status=405)
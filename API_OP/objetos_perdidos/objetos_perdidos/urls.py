from django.contrib import admin
from django.urls import path,include
from rest_framework.routers import DefaultRouter
from api.views import TipoUsuarioViewSet, UsuarioViewSet, ObjetoViewSet,login_view

router = DefaultRouter()
router.register(r'tipo_usuario', TipoUsuarioViewSet)
router.register(r'usuario', UsuarioViewSet)
router.register(r'objeto', ObjetoViewSet)

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/', include(router.urls)),
    path('api/login/', login_view),  # Ruta para el endpoint de login
]

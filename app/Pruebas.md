# Pruebas

## Sesión 5

> Rellena la columna **Resultado obtenido** tras probar cada caso en el emulador.

| #    | Prueba | Resultado esperado | Resultado obtenido |
|------|--------|--------------------|--------------------|
| P5-1 | Abrir el detalle del café, pulsar + dos veces y volver con la flecha | La carta muestra 2 cafés y el total correcto | |
| P5-2 | Pulsar "Ver pedido" dos veces muy rápido y luego Atrás. Repetir quitando temporalmente `launchSingleTop` | Con `launchSingleTop`, un solo Atrás vuelve a la carta | |
| P5-3 | En el resumen, pulsar "Confirmar" sin nombre | Aparece el error del nombre y no se abre el diálogo | |
| P5-4 | Enviar un pedido y, en "Pedido enviado", pulsar Atrás del sistema | Vuelve a la carta (no al resumen) y el pedido está vacío | |
| P5-5 | Abrir el resumen con el pedido vacío | Se ve `pedido_vacio` y "Confirmar" está desactivado | |
| P5-6 | Girar la pantalla en el detalle de un producto con cantidad | Misma pantalla, mismo producto y misma cantidad | |
| P5-7 | Logcat filtrado por `tag:PedidoVM`, recorrer las cuatro pantallas | "PedidoViewModel creado" aparece una sola vez | |
| P5-8 | Experimento: `viewModel()` propio dentro de `composable<Detalle>` | Documentar cantidad y Logcat (apartado 5.8). **Deshacer el cambio** | |
| P5-9 | *No conservar actividades*: añadir cantidad en el detalle, Inicio y volver | Documentar pantalla y cantidad (apartado 5.9) | |

### P5-2 — "Ver pedido" pulsado dos veces

```
Con launchSingleTop               Sin launchSingleTop
┌──────────┐                      ┌──────────┐
│ Resumen  │ ← visible            │ Resumen  │ ← visible
├──────────┤                      ├──────────┤
│ Carta    │                      │ Resumen  │ ← ¡repetida!
└──────────┘                      ├──────────┤
                                  │ Carta    │
                                  └──────────┘
```

Sin `launchSingleTop`: _(escribe cuántos Atrás hacen falta)_

### P5-4 — Enviar el pedido (`popUpTo<Carta>()`)

```
Antes de navegar     popUpTo<Carta>     + PedidoEnviado
┌──────────┐                            ┌───────────────┐
│ Resumen  │                            │ PedidoEnviado │ ← visible
├──────────┤         ┌──────────┐       ├───────────────┤
│ Carta    │         │ Carta    │       │ Carta         │
└──────────┘         └──────────┘       └───────────────┘
```

Atrás (o "Volver a la carta" → `popBackStack()`) deja solo `Carta`.

### P5-8 — ViewModel dentro de `composable<Detalle>`

_(Cantidad que se ve, mensajes de Logcat al entrar/salir y explicación)_

### P5-9 — No conservar actividades

_(Pantalla que aparece, qué pasa con la cantidad y explicación)_

package com.example.consciente_te.pages

sealed class AppPages(val route: String) {
    object HomePage : AppPages(route = "home")
    object AlbumCataloguePage : AppPages(route = "albumCatalogue")
    object Registry:AppPages(route="signing")
    object MediaPage:AppPages(route = "media_list")
    object CreateTask:AppPages(route = "create_task")
    object CreateAlarm:AppPages(route = "create_alarm")
    object ListAlarm:AppPages(route = "list_alarm")
    object AlbumDetailPage : AppPages(route = "albumDetail")
    object AlbumCreatePage : AppPages(route = "albumCreate")
    object ArtistCataloguePage : AppPages(route = "artistCatalogue")
    object ArtistDetailPage : AppPages(route = "artistDetail")
    object CollectorCataloguePage : AppPages(route = "collectorCatalogue")
    object CollectorDetailPage : AppPages(route = "collectorDetail")
}

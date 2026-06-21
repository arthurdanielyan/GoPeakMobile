import SwiftUI
import SharedLogic

@main
struct iOSApp: App {

    // Mirrors `GoPeakApplication.onCreate()` on Android: start Koin once, before any
    // component is resolved.
    init() {
        KoinInitializerKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            RootView()
        }
    }
}

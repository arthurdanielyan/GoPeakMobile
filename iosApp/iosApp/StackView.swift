//
//  StackView.swift
//  iosApp
//
//  Adapted from the Decompose iOS sample:
//  https://github.com/arkivanov/Decompose/blob/master/sample/app-ios/app-ios/DecomposeHelpers/StackView.swift
//
//  Generics are flipped vs. the original so the API matches this project's stack:
//   - `T` is the stack *configuration* (a Kotlin sealed interface, e.g. `RootScreenConfig`).
//     Obj-C protocols are class-bound, so `T: AnyObject` is satisfied by it.
//   - the child *component instance* is `Any` in Kotlin (`ChildStack<Config, Any>`), so it
//     bridges to Swift `Any`; it is handed to `childContent` as `AnyObject` so you can
//     switch/downcast it, exactly like Kotlin's `when (it.instance) { is LoginComponent -> ... }`.
//
//  A custom `UINavigationController` gives native push/pop + interactive swipe-back.
//

import SharedLogic
import SwiftUI

struct StackView<T: AnyObject, Content: View>: View {
  @ObservedObject var stackValue: ObservableValue<ChildStack<T, AnyObject>>
  var onBack: () -> Void
  @ViewBuilder var childContent: (AnyObject) -> Content

  private var components: [AnyObject] {
    stackValue.value.items.map { $0.instance }
  }

  var body: some View {
    StackInteropView(
      components: components,
      onBack: onBack,
      childContent: childContent
    )
    .ignoresSafeArea(.container)
  }
}

private struct StackInteropView<Content: View>: UIViewControllerRepresentable {
  var components: [AnyObject]
  var onBack: () -> Void
  var childContent: (AnyObject) -> Content

  func makeCoordinator() -> Coordinator {
    Coordinator(self)
  }

  func makeUIViewController(context: Context) -> UINavigationController {
    context.coordinator.syncChanges(self)
    let navigationController = CustomNavigationController(
      rootViewController: context.coordinator.viewControllers.first!
    )

    return navigationController
  }

  func updateUIViewController(
    _ navigationController: UINavigationController,
    context: Context
  ) {
    context.coordinator.syncChanges(self)
    navigationController.setViewControllers(
      context.coordinator.viewControllers,
      animated: true
    )
  }

  private func createViewController(
    _ component: AnyObject,
    _ coordinator: Coordinator
  ) -> NavigationItemHostingController {
    let controller = NavigationItemHostingController(
      rootView: childContent(component)
    )
    controller.coordinator = coordinator
    controller.component = component
    controller.onBack = onBack

    return controller
  }

  private final class CustomNavigationController: UINavigationController, UIGestureRecognizerDelegate {
    override func viewDidLoad() {
      super.viewDidLoad()
      navigationBar.isHidden = true
      interactivePopGestureRecognizer?.delegate = self
    }

    // fixes swipes back, when parent stack view intercepts child's gestures
    func gestureRecognizerShouldBegin(_ gestureRecognizer: UIGestureRecognizer) -> Bool {
      viewControllers.count > 1
    }
  }

  fileprivate final class Coordinator: NSObject {
    var parent: StackInteropView<Content>
    var viewControllers = [NavigationItemHostingController]()
    var preservedComponents = [AnyObject]()

    init(_ parent: StackInteropView<Content>) {
      self.parent = parent
    }

    func syncChanges(_ parent: StackInteropView<Content>) {
      self.parent = parent
      let count = max(preservedComponents.count, parent.components.count)

      for i in 0..<count {
        if (i >= parent.components.count) {
          viewControllers.removeLast()
        } else if (i >= preservedComponents.count) {
          viewControllers.append(parent.createViewController(parent.components[i], self))
        } else if (parent.components[i] !== preservedComponents[i]) {
          viewControllers[i] = parent.createViewController(parent.components[i], self)
        }
      }

      preservedComponents = parent.components
    }
  }

  fileprivate final class NavigationItemHostingController: UIHostingController<Content> {
    fileprivate(set) weak var coordinator: Coordinator?
    fileprivate(set) var component: AnyObject?
    fileprivate(set) var onBack: (() -> Void)?

    override func viewDidDisappear(_ animated: Bool) {
      super.viewDidDisappear(animated)

      if isMovingFromParent && coordinator?.preservedComponents.last === component {
        onBack?()
      }
    }
  }
}

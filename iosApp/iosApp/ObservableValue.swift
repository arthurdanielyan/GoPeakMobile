//
//  ObservableValue.swift
//  iosApp
//
//  Companion helper for StackView.swift — wraps a Decompose `Value<T>` as a SwiftUI
//  `ObservableObject`. From the Decompose iOS sample:
//  https://github.com/arkivanov/Decompose/blob/master/sample/app-ios/app-ios/DecomposeHelpers/ObservableValue.swift
//

import SharedLogic
import SwiftUI

public class ObservableValue<T: AnyObject>: ObservableObject {

    private let observableValue: Value<T>

    @Published var value: T

    private var cancellation: Cancellation?

    init(_ value: Value<T>) {
        self.observableValue = value
        self.value = observableValue.value
        self.cancellation = observableValue.subscribe { [weak self] value in
            self?.value = value
        }
    }

    deinit {
        cancellation?.cancel()
    }
}

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
    
    @Published var value: T

    private var cancellation: Cancellation?

    init(_ value: Value<T>) {
        self.value = value.value
        self.cancellation = value.subscribe { [weak self] value in
            self?.value = value
        }
    }

    deinit {
        cancellation?.cancel()
    }
}

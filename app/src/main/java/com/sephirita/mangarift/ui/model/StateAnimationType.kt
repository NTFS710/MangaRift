package com.sephirita.mangarift.ui.model

enum class StateAnimationType {
    FLIPPING_PAGES {
        override val animationUrl: String
            get() = "https://lottie.host/90671885-e53d-44c0-a4f5-38b712f2e5d8/1Kp9NsU3wo.json"
    },
    DETAILED_PAGES {
        override val animationUrl: String
            get() = "https://lottie.host/525687df-0a4c-4d47-92b5-093c6a2d0861/2BMSYMB0tg.json"
    },
    NONE {
        override val animationUrl: String
            get() = ""
    };

    abstract val animationUrl: String
}
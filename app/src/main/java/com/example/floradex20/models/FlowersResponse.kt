package com.example.floradex20.models

data class FlowersResponse (
    val `data`: List<Flower>,
    val links: FlowersResponseLinks,
    val meta: FlowersResponseMetadata
)
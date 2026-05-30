## 2024-05-30 - Eager Fetching in JPA
**Learning:** In JPA, `@ManyToOne` relationships are fetched eagerly by default. In `RecommendationModel`, the `activity` relation was using the default eager fetching, which could cause N+1 query issues or load unnecessary data when retrieving recommendations.
**Action:** Always explicitly specify `fetch = FetchType.LAZY` for `@ManyToOne` and `@OneToOne` associations to avoid unwanted queries, unless eager loading is specifically required.

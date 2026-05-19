### Wybrór:
#### Strategy Pattern

### Powód: 
Promocje powinny być aplikowane dynamicznie, bez wpływu na sam koszyk.
Koszyk w implementacji spełnia zadanie klienta wykorzystującego daną strategię.
Sprzyja to także architekturze projektu - promocja zwraca PromotionResult. On zawiera listę Discount, na której podstawie dopiero obliczamy cenę.

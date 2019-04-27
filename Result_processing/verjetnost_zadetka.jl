

function blizina(n::Int)::Int
    
    return Int(round(2 * n * (n * (2n + 3) + 7) / 3 + 1))
end

function verjetnost_zadetka(n::Int)::Float64
    
    return blizina(n) / 2^24
end

function najdi_odstopanje_za_verjetnost(p::Float64)::Int
    
    n = 0
    
    while verjetnost_zadetka(n) < p
        n += 1
    end
    
    if abs(verjetnost_zadetka(n - 1) - p) < abs(verjetnost_zadetka(n) - p)
        n -= 1
    end
    
    return n
end

nothing

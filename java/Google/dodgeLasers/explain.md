Beatty sequence
This problem require to calculate ∑ni=1⌊i2‾√⌋. We must take into consideration the precision and the performance. The n can be very large, up to 101 digits. There several aspect. How to take floor more efficiently? How to deal with big number? After a few search I found that there is a specific algorithm. This type of problem involves a mathematical concept: Beatty sequence. In mathematics, a Beatty sequence is the sequence of integers found by taking the floor of the positive multiples of a positive irrational number.

r=⌊r⌋, ⌊2r⌋, ⌊3r⌋,…

Let (i)r=⌊i∗r⌋ for some irrational positive number,

and

S(r,n)=∑ni=1(i)r

If r≥2 we let s=r−1 and we have S(r,n)=S(s,n)+∑ni=1i=S(s,n)+n(n+1)2

If 1<r<2 there is a theorem that says that if s satisfies r−1+s−1=1, then the sequences r and s for n≥1 partition ℕ (not counting 0).

Therefore, S(r,n)+S(s,⌊(n)rs⌋)=∑(n)rii=(n)r((n)r+1)2

And also ⌊(n)rs⌋=⌊(n)r(1−1r)⌋=(n)r−⌈(n)rr⌉=(n)r−n

Then, letting n′=⌊(r−1)n⌋=nr−1

we have S(r,n)=(n)r((n)r+1)2−S(s,n′)=(n+n′)(n+n′+1)2−S(s,n′).

Back to the problem, we have r=2‾√, so we start with s=2+2‾√. We can get a recurrence formula.

Let n′=⌊(2‾√−1)n⌋,

S(2‾√,n)=(n+n′)(n+n′+1)2−S(2+2‾√,n′)=(n+n′)(n+n′+1)2−(n′(n′+1)−S(2‾√,n′))=nn′+n(n+1)2−n′(n′+1)2−S(2‾√,n′)
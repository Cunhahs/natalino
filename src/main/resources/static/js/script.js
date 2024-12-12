
document.addEventListener("DOMContentLoaded", () => {
  const decodeButton = document.getElementById("decode-button");
  const inputField = document.getElementById("base64-input");
  const decodedTextElement = document.getElementById("decoded-text");
  const canvas = document.getElementById("canvas");
  const ctx = canvas.getContext("2d");

  // Ajustar o tamanho do canvas
  canvas.width = window.innerWidth;
  canvas.height = window.innerHeight;

  // Função para enviar Base64 ao backend
  async function decodeBase64(base64) {
    try {
      const response = await fetch("/api/decode", {
        method: "POST",
        headers: {
          "Content-Type": "text/plain",
        },
        body: base64,
      });

      if (!response.ok) {
        throw new Error("Erro na comunicação com o servidor");
      }

      return await response.text();
    } catch (error) {
      console.error("Erro ao decodificar:", error);
      return "Erro: Não foi possível decodificar o texto!";
    }
  }

  // Mostrar o texto decodificado na página
  async function handleDecode() {
    const base64Text = inputField.value.trim();

    // Validação do campo de entrada
    if (!base64Text) {
      alert("Por favor, insira um texto em Base64.");
      return;
    }

    // Enviar o texto para o backend e receber a resposta
    const decodedText = await decodeBase64(base64Text);

    // Atualizar o texto no contêiner de resultado
    decodedTextElement.textContent = decodedText;

    // Iniciar animações no fundo
    startAnimation();
  }

  // Evento de clique no botão
  decodeButton.addEventListener("click", handleDecode);

  // Função de animação
  function startAnimation() {
    const particles = [];
    const colors = ["#FF0000", "#00FF00", "#0000FF", "#FFFF00", "#FF00FF", "#00FFFF"];

    function createParticle() {
      particles.push({
        x: Math.random() * canvas.width,
        y: Math.random() * canvas.height,
        size: Math.random() * 5 + 1,
        color: colors[Math.floor(Math.random() * colors.length)],
        speedX: (Math.random() - 0.5) * 2,
        speedY: (Math.random() - 0.5) * 2,
      });
    }

    function updateParticles() {
      particles.forEach((p, index) => {
        p.x += p.speedX;
        p.y += p.speedY;
        if (p.size > 0.2) p.size -= 0.1;
        else particles.splice(index, 1);
      });
    }

    function drawParticles() {
      ctx.clearRect(0, 0, canvas.width, canvas.height);
      particles.forEach(p => {
        ctx.beginPath();
        ctx.arc(p.x, p.y, p.size, 0, Math.PI * 2);
        ctx.fillStyle = p.color;
        ctx.fill();
      });
    }

    function loop() {
      createParticle();
      updateParticles();
      drawParticles();
      requestAnimationFrame(loop);
    }

    loop();
  }
});

const PIPEDREAM_URL = "https://eomzn1w9nczln6s.m.pipedream.net";

document.addEventListener('DOMContentLoaded', () => {
  const feesBtn = document.getElementById('feesBtn');
  const modal = document.getElementById('modal');
  const closeModal = document.getElementById('closeModal');
  const feesContent = document.getElementById('feesContent');

  feesBtn.addEventListener('click', async () => {
    openModal();
    feesContent.innerHTML = 'Loading...';
    const univ = window.UNIVERSITY_CODE || document.getElementById('universityCode').value;
    try {
      const res = await fetch(`/api/fees/${encodeURIComponent(univ)}`);
      if (!res.ok) throw new Error('Failed to fetch fees');
      const data = await res.json();
      renderFees(data);
    } catch (e) {
      feesContent.innerHTML = '<div class="error">Unable to load fees.</div>';
    }
  });

  closeModal.addEventListener('click', closeModalFn);
  modal.addEventListener('click', (ev) => {
    if (ev.target === modal) closeModalFn();
  });
});

function openModal() {
  const modal = document.getElementById('modal');
  modal.setAttribute('aria-hidden', 'false');
}

function closeModalFn() {
  const modal = document.getElementById('modal');
  modal.setAttribute('aria-hidden', 'true');
}

function renderFees(data) {
  const feesContent = document.getElementById('feesContent');

  if (!data || !data.courses) {
    feesContent.innerHTML = '<div class="error">No fee data available.</div>';
    return;
  }

  let html = `<h3 class="modal-title">Fee Structure</h3>`;

  data.courses.forEach(c => {
    const breakdown = JSON.parse(c.breakdownJson || "{}");

    html += `
      <div class="fee-card">
        <div class="fee-header">
          <h4>${escapeHtml(c.name)} <span>(${escapeHtml(c.code)})</span></h4>
        </div>

        <div class="fee-range">
          <span class="badge">₹${formatNumber(c.minFee)} – ₹${formatNumber(c.maxFee)}</span>
          <label class="range-label">Total Fee Range</label>
        </div>

        <div class="breakdown">
          <h5>Breakdown</h5>
          <div class="breakdown-body">
    `;

    Object.entries(breakdown).forEach(([key, value]) => {
      html += `
        <div class="bd-row">
          <span class="bd-key">${formatKey(key)}</span>
          <span class="bd-value">₹${formatNumber(value)}</span>
        </div>
      `;
    });

    html += `
          </div>
        </div>
      </div>
    `;
  });

  feesContent.innerHTML = html;
}

function formatKey(k) {
  return k.replace(/_/g, " ").replace(/\b\w/g, c => c.toUpperCase());
}


function formatNumber(n) {
  if (!n) return '0';
  return n.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function escapeHtml(unsafe) {
  return (unsafe || '').toString()
    .replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/>/g, "&gt;");
}

// Lead submission
async function submitLead(e) {
  e.preventDefault();
  const submitBtn = document.getElementById('submitBtn');
  const status = document.getElementById('formStatus');
  const universityCode = document.getElementById('universityCode').value || window.UNIVERSITY_CODE;

  const data = {
    universityCode,
    name: document.getElementById('name').value.trim(),
    email: document.getElementById('email').value.trim(),
    phone: document.getElementById('phone').value.trim(),
    state: document.getElementById('state').value,
    course: document.getElementById('course').value,
    intake: document.getElementById('intake').value,
    consent: document.getElementById('consent').checked
  };

  // Basic validation
  if (!data.name || !data.email || !data.phone) {
    status.textContent = "Please fill required fields.";
    return;
  }
  const phoneValid = /^[6-9]\d{9}$/;
  if (!phoneValid.test(data.phone)) {
    status.textContent = "Enter valid 10-digit Indian mobile number.";
    return;
  }
  if (!data.consent) {
    status.textContent = "Please agree to be contacted.";
    return;
  }

  submitBtn.disabled = true;
  status.textContent = "Sending...";

  // First: send to Pipedream (required by assignment)
  let pdOk = false;
  try {
    const r1 = await fetch(PIPEDREAM_URL, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data)
    });
    pdOk = r1.ok;
  } catch (err) {
    pdOk = false;
  }

  // Second: store in our backend DB (optional)
  let saveOk = false;
  try {
    const r2 = await fetch('/api/leads', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data)
    });
    saveOk = r2.ok;
  } catch (err) {
    saveOk = false;
  }

  if (pdOk) {
    status.textContent = "Thank you — we received your details.";
    document.getElementById('leadForm').reset();
  } else {
    status.textContent = "Submission failed to Pipedream. Please try again later.";
  }

  // Optionally show whether saved locally
  if (!saveOk) {
    console.warn("Local save failed");
  }

  submitBtn.disabled = false;
}
